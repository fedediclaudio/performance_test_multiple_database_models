package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.mongo.Association;
import ar.edu.unlp.info.bd2.repositories.MongoDBBithubRepository;
import org.bson.types.ObjectId;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class MongoDBBithubServiceImplementation implements BithubService<ObjectId> {

    public MongoDBBithubRepository repository;

    public MongoDBBithubServiceImplementation(MongoDBBithubRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public User createUser(String email, String name) {
        User user = new User(email, name);
        this.repository.save(user, User.class);
        return user;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return this.repository.getUserByEmail(email);
    }

    @Override
    @Transactional
    public Branch createBranch(String name) {
        Branch branch = new Branch(name);
        this.repository.save(branch, Branch.class);
        return branch;
    }

    @Override
    @Transactional
    public Commit createCommit(String description, String hash, User author, List<File> files, Branch branch) {
        Commit commit = new Commit(description,hash,author,files,branch);
        this.repository.saveCommit(commit);
        return commit;
    }

    @Override
    @Transactional
    public Tag createTagForCommit(String commitHash, String name) throws BithubException {
        Optional<Commit> commit = this.getCommitByHash(commitHash);
        if (!commit.isPresent()) {
            throw new BithubException("No existe commit con ese hash");
        }
        Tag tag = new Tag(commit.get(), name);
        this.repository.saveTag(tag);
        return tag;
    }

    @Override
    public Optional<Commit> getCommitByHash(String commitHash) {
        return this.repository.getCommitByHash(commitHash);
    }

    @Override
    @Transactional
    public File createFile(String name, String content) {
        File file = new File(name, content);
        this.repository.save(file, File.class);
        return file;
    }

    @Override
    public Optional<Tag> getTagByName(String tagName) {
        return this.repository.getTagByName(tagName);
    }

    @Override
    @Transactional
    public Review createReview(Branch branch, User user) {
        Review review = new Review(branch, user);
        this.repository.saveReview(review);
        return review;
    }

    @Override
    @Transactional
    public FileReview addFileReview(Review review, File file, int lineNumber, String comment) throws BithubException {
        if(review.getBranch().getObjectId() != file.getCommit().getBranch().getObjectId()){
            throw new BithubException("El branch donde se quiere hacer el review no corresponde al branch del archivo");
        }
        FileReview fileReview = new FileReview(review, file, lineNumber, comment);
        this.repository.saveFileReview(fileReview);
        return  fileReview;
    }

    @Override
    public Optional<Review> getReviewById(ObjectId id) {
        return this.repository.getReviewById(id);
    }

    @Override
    public List<Commit> getAllCommitsForUser(ObjectId userId) {
        Optional<User> user = this.repository.getUserAndCommitsById(userId);
        return (user.isPresent()) ? user.get().getCommits():Arrays.asList();
    }

    @Override
    public Map<ObjectId, Long> getCommitCountByUser() {
        List<User> users = this.repository.allUsers();
        Map<ObjectId, Long> map = new HashMap<ObjectId, Long>();
        for(User u : users) {
            map.put(u.getObjectId(), (long) u.getCommits().size());
        }
        return map;
    }

    @Override
    public List<User> getUsersThatCommittedInBranch(String branchName) throws BithubException {
        Optional<Branch> branch = this.getBranchByName(branchName);
        if(! branch.isPresent()) {
            throw new BithubException("No existe el branch");
        }
        else {
            Set<User> users = new HashSet<User>();
            Set<ObjectId> usersId = new HashSet<ObjectId>();
            for(Commit c : branch.get().getCommits()){
                if(!usersId.contains(c.getAuthor().getObjectId())) {
                    usersId.add(c.getAuthor().getObjectId());
                    users.add(c.getAuthor());
                }
            }
            return new ArrayList<User>(users);
        }
    }

    @Override
    public Optional<Branch> getBranchByName(String branchName) {
        return this.repository.getBranchByName(branchName);
    }
}

