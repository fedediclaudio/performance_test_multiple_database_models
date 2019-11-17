package ar.edu.unlp.info.bd2.repositories;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.mongo.Association;
import com.mongodb.client.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.Doc;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.regex;

public class MongoDBBithubRepository {

  @Autowired private MongoClient client;

  public MongoDatabase getDatabase() {
    return this.client.getDatabase("bd2");
  }

  public void save(PersistentObject ob, Class c) {
    this.getDatabase().getCollection(c.getSimpleName(), c).insertOne(ob);
  }

  public void saveAssociation(Association association, String collectionName) {
    this.getDatabase().getCollection(collectionName, Association.class).insertOne(association);
  }

  public List<Commit> iteratorCommitsToList(AggregateIterable<Commit> iterator) {
    List<Commit> list = new ArrayList<>();
    iterator.iterator().forEachRemaining(list::add);
    return list;
  }

  public List<FileReview> iteratorFileReviewsToList(AggregateIterable<FileReview> iterator) {
    List<FileReview> list = new ArrayList<>();
    iterator.iterator().forEachRemaining(list::add);
    return list;
  }

  public List<User> iteratorUsersToList(FindIterable<User> iterator) {
    List<User> list = new ArrayList<>();
    iterator.iterator().forEachRemaining(list::add);
    return list;
  }

  public void saveCommit(Commit commit) {
    this.save(commit, Commit.class);
    this.saveAssociation(new Association(commit.getObjectId(), commit.getBranch().getObjectId()), "Commit_Branch");
    this.saveAssociation(new Association(commit.getObjectId(), commit.getAuthor().getObjectId()), "Commit_User");
  }

  public Optional<Commit> getCommitByHash(String hash) {
    Commit commit = this.getDatabase().getCollection(Commit.class.getSimpleName(), Commit.class).aggregate(
            Arrays.asList(
              Aggregates.match(Filters.eq("hash", hash))
            )
    ).first();
    if (commit == null) { return Optional.empty(); }
    Branch branch = this.getDatabase().getCollection("Commit_Branch", Branch.class).aggregate(
            Arrays.asList(
              Aggregates.match(Filters.eq("source", commit.getObjectId())),
              Aggregates.lookup("Branch", "destination", "_id", "branch"),
              Aggregates.unwind("$branch"),
              Aggregates.replaceRoot("$branch")
            )
    ).first();
    User author = this.getDatabase().getCollection("Commit_User", User.class).aggregate(
            Arrays.asList(
              Aggregates.match(Filters.eq("source", commit.getObjectId())),
              Aggregates.lookup("User", "destination", "_id", "author"),
              Aggregates.unwind("$author"),
              Aggregates.replaceRoot("$author")
            )
    ).first();
    commit.setBranch(branch);
    branch.getCommits().add(commit);
    commit.setAuthor(author);
    author.getCommits().add(commit);
    return Optional.of(commit);
  }

  public Commit getCommitByID(ObjectId id) {
    Commit commit = this.getDatabase().getCollection(Commit.class.getSimpleName(), Commit.class).aggregate(
            Arrays.asList(
                    Aggregates.match(Filters.eq("_id", id))
            )
    ).first();
    User author = this.getDatabase().getCollection("Commit_User", User.class).aggregate(
            Arrays.asList(
                    Aggregates.match(Filters.eq("source", commit.getObjectId())),
                    Aggregates.lookup("User", "destination", "_id", "author"),
                    Aggregates.unwind("$author"),
                    Aggregates.replaceRoot("$author")
            )
    ).first();
    commit.setAuthor(author);
    author.getCommits().add(commit);
    return commit;
  }


  public Optional<Branch> getBranchByName(String branchName) {
    Branch branch = this.getDatabase().getCollection("Branch", Branch.class).find(Filters.eq("name", branchName)).first();
    if(branch == null) { return Optional.empty(); }
    FindIterable<Association> associationsCommits = this.getDatabase().getCollection("Commit_Branch", Association.class).find();
    List<Commit> commits = new ArrayList<>();
    associationsCommits.iterator().forEachRemaining(association -> commits.add(this.getCommitByID(association.getSource())));
    branch.setCommits(commits);
    commits.iterator().forEachRemaining(commit -> commit.setBranch(branch));
    return Optional.of(branch);
  }

  public void saveTag(Tag tag) {
    this.save(tag,Tag.class);
    this.saveAssociation(new Association(tag.getObjectId(), tag.getCommit().getObjectId()), "Tag_Commit");
  }

  public Optional<Tag> getTagByName(String tagName) {
    Tag tag = this.getDatabase().getCollection(Tag.class.getSimpleName(), Tag.class).find(Filters.eq("name",tagName)).first();
    if(tag == null) { return Optional.empty(); }
    Commit commit = this.getDatabase().getCollection("Tag_Commit", Commit.class).aggregate(
            Arrays.asList(
                Aggregates.match(Filters.eq("source", tag.getObjectId())),
                Aggregates.lookup("Commit", "destination", "_id", "commit"),
                Aggregates.unwind("$commit"),
                Aggregates.replaceRoot("$commit")
            )
    ).first();
    tag.setCommit(commit);
    return Optional.of(tag);
  }

  public void saveReview(Review review) {
    this.save(review, Review.class);
    this.saveAssociation(new Association(review.getObjectId(), review.getBranch().getObjectId()), "Review_Branch");
    this.saveAssociation(new Association(review.getObjectId(), review.getAuthor().getObjectId()), "Review_User");
  }

  public void saveFileReview(FileReview fileReview) {
    this.save(fileReview, FileReview.class);
    this.saveAssociation(new Association(fileReview.getReview().getObjectId(), fileReview.getObjectId()), "Review_FileReview");
  }

  public Optional<Review> getReviewById(ObjectId id) {
    Review review = this.getDatabase().getCollection(Review.class.getSimpleName(), Review.class).find(Filters.eq("_id", id)).first();
    if (review == null) { return Optional.empty(); }
    Branch branch = this.getDatabase().getCollection("Review_Branch", Branch.class).aggregate(
            Arrays.asList(
                    Aggregates.match(Filters.eq("source", review.getObjectId())),
                    Aggregates.lookup("Branch", "destination", "_id", "branch"),
                    Aggregates.unwind("$branch"),
                    Aggregates.replaceRoot("$branch")
            )
    ).first();
    User author = this.getDatabase().getCollection("Review_User", User.class).aggregate(
            Arrays.asList(
                    Aggregates.match(Filters.eq("source", review.getObjectId())),
                    Aggregates.lookup("User", "destination", "_id", "author"),
                    Aggregates.unwind("$author"),
                    Aggregates.replaceRoot("$author")
            )
    ).first();
    AggregateIterable<FileReview> fileReviews = this.getDatabase().getCollection("Review_FileReview", FileReview.class).aggregate(
            Arrays.asList(
                    Aggregates.match(Filters.eq("source", review.getObjectId())),
                    Aggregates.lookup("FileReview", "destination", "_id", "fileReviews"),
                    Aggregates.unwind("$fileReviews"),
                    Aggregates.replaceRoot("$fileReviews")
            )
    );
    review.setAuthor(author);
    review.setBranch(branch);
    review.setFileReviews(this.iteratorFileReviewsToList(fileReviews));
    fileReviews.iterator().forEachRemaining(fileReview -> fileReview.setReview(review));
    return Optional.of(review);
  }

  public AggregateIterable<Commit> getCommitsOfUser(ObjectId id) {
    AggregateIterable<Commit> commits = this.getDatabase().getCollection("Commit_User", Commit.class).aggregate(
            Arrays.asList(
                    Aggregates.match(Filters.eq("destination", id)),
                    Aggregates.match(Filters.eq("destination", id)),
                    Aggregates.lookup("Commit", "source", "_id", "commits"),
                    Aggregates.unwind("$commits"),
                    Aggregates.replaceRoot("$commits")
            )
    );
    return commits;
  }

  public Optional<User> getUserAndCommitsById(ObjectId id) {
    User user = this.getDatabase().getCollection(User.class.getSimpleName(), User.class).find(Filters.eq("_id", id)).first();
    if(user == null) { return Optional.empty(); }
    AggregateIterable<Commit> commits = this.getCommitsOfUser(user.getObjectId());
    user.setCommits(iteratorCommitsToList(commits));
    commits.iterator().forEachRemaining(commit -> commit.setAuthor(user));
    return Optional.of(user);
  }

  public List<User> allUsers(){
    FindIterable<User> usersIterable = this.getDatabase().getCollection("User", User.class).find();
    List<User> users = this.iteratorUsersToList(usersIterable);
    for (User u: users) {
      u.setCommits(this.iteratorCommitsToList(this.getCommitsOfUser(u.getObjectId())));
    }
    return users;
  }

  public Optional<User> getUserByEmail(String email) {
    User user = this.getDatabase().getCollection(User.class.getSimpleName(), User.class).find(Filters.eq("email", email)).first();
    if (user == null) { return Optional.empty(); }
    AggregateIterable<Commit> commits = this.getCommitsOfUser(user.getObjectId());
    user.setCommits(iteratorCommitsToList(commits));
    commits.iterator().forEachRemaining(commit -> commit.setAuthor(user));
    return Optional.of(user);
  }
}
