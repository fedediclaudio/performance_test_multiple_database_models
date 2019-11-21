package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.spring.data.sql.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class SpringDataBithubService implements BithubService<Long> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private CommitRepository commitRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private FileReviewRepository fileReviewRepository;

    @Override
    public User createUser(String email, String name) {
        User user = new User(email, name);
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        List<User> users = userRepository.findByEmail(email);
        return (!users.isEmpty()) ? Optional.of(users.get(0)):Optional.empty();
    }

    @Override
    public Branch createBranch(String name) {
        Branch branch = new Branch(name);
        branchRepository.save(branch);
        return branch;
    }

    @Override
    public Commit createCommit(String description, String hash, User author, List<File> files, Branch branch) {
        Commit commit = new Commit(description, hash, author, files, branch);
        commitRepository.save(commit);
        return commit;
    }

    @Override
    public Tag createTagForCommit(String commitHash, String name) throws BithubException {
        Optional<Commit> commit = this.getCommitByHash(commitHash);
        if(!commit.isPresent()) {
            throw new BithubException("El commit no existe");
        }
        Tag tag = new Tag(commit.get(), name);
        tagRepository.save(tag);
        return tag;
    }

    @Override
    public Optional<Commit> getCommitByHash(String commitHash) {
        List<Commit> commits = this.commitRepository.findByHash(commitHash);
        return (!commits.isEmpty()) ? Optional.of(commits.get(0)):Optional.empty();
    }

    @Override
    public File createFile(String name, String content) {
        File file = new File(name, content);
        fileRepository.save(file);
        return file;
    }

    @Override
    public Optional<Tag> getTagByName(String tagName) {
        List<Tag> tag = this.tagRepository.findByName(tagName);
        return (!tag.isEmpty()) ? Optional.of(tag.get(0)):Optional.empty();
    }

    @Override
    public Review createReview(Branch branch, User user) {
        Review review = new Review(branch, user);
        reviewRepository.save(review);
        return review;
    }

    @Override
    public FileReview addFileReview(Review review, File file, int lineNumber, String comment) throws BithubException {
        if(review.getBranch().getId() != file.getCommit().getBranch().getId()){
            throw new BithubException("El branch donde se quiere hacer el review no corresponde al branch del archivo");
        }
        FileReview fileReview = new FileReview(review, file, lineNumber, comment);
        fileReviewRepository.save(fileReview);
        return fileReview;
    }

    @Override
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Commit> getAllCommitsForUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return (user.isPresent()) ? user.get().getCommits():Arrays.asList();
    }

    @Override
    public Map<Long, Long> getCommitCountByUser() {
        List<User> users = userRepository.findAll();
        Map<Long, Long> map = new HashMap<Long, Long>();
        for(User u : users) {
            map.put(u.getId(), (long) u.getCommits().size());
        }
        return map;
    }

    @Override
    public List<User> getUsersThatCommittedInBranch(String branchName) throws BithubException {
        List<Branch> branch = branchRepository.findByName(branchName);
        if(branch.isEmpty()) {
            throw new BithubException("No existe el branch");
        }
        Set<User> users = new HashSet<User>();
        for(Commit c : branch.get(0).getCommits()){
            users.add(c.getAuthor());
        }
        return new ArrayList<User>(users);
    }

    @Override
    public Optional<Branch> getBranchByName(String branchName) {
        Branch branch = branchRepository.findByName(branchName).get(0);
        return (branch != null) ? Optional.of(branch):Optional.empty();
    }
}
