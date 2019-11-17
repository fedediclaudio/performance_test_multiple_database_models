package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.repositories.HibernateBithubRepository;
import ar.edu.unlp.info.bd2.model.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class HibernateBithubService implements BithubService<Long> {

    private HibernateBithubRepository repository;

    public HibernateBithubService(HibernateBithubRepository repository){
        this.repository = repository;
    }

    @Transactional
    public User createUser(String email, String name){
        User usuario = new User(email, name);
        this.repository.save(usuario);
        return usuario;
    }

    public Optional<User> getUserByEmail(String email){
        return this.repository.getUserByEmail(email);
    }

    public Optional<User> getUserById(Long id){
        return this.repository.getUserById(id);
    }

    @Transactional
    public Branch createBranch(String name){
        Branch branch = new Branch(name);
        this.repository.save(branch);
        return branch;
    }

    @Transactional
    public Commit createCommit(String message, String hash, User author, List<File> files, Branch branch){
        Commit commit = new Commit(message, hash, author,files,branch);
        this.repository.save(commit);
        return commit;
    }

    @Transactional
    public Tag createTagForCommit(String commitHash, String name) throws BithubException{
        Optional<Commit> commit = this.repository.getCommitByHash(commitHash);
        if(commit.isPresent()) {
            Tag tag = new Tag(commit.get(), name);
            this.repository.save(tag);
            return tag;
        } else {
            throw new BithubException("No existe Commit con ese hash");
        }
    }

    public Optional<Commit> getCommitByHash(String commitHash){
        return this.repository.getCommitByHash(commitHash);
    }

    @Transactional
    public File createFile(String name, String content){
        File file = new File(name, content);
        this.repository.save(file);
        return file;
    }

    public Optional<Tag> getTagByName(String tagName){
        return this.repository.getTagByName(tagName);
    }

    @Transactional
    public Review createReview(Branch branch, User user){
        Review review = new Review(branch,user);
        this.repository.save(review);
        return review;
    }

    @Transactional
    public FileReview addFileReview(Review review, File file, int lineNumber, String comment) throws BithubException{
        if(review.getBranch().getId() != file.getCommit().getBranch().getId()){
            throw new BithubException("El branch donde se quiere hacer el review no corresponde al branch del archivo");
        }
        FileReview fileReview = new FileReview(review, file, lineNumber, comment);
        this.repository.save(fileReview);
        return  fileReview;
    }

    @Override
    public Optional<Review> getReviewById(Long id){
        return this.repository.getReviewById(id);
    }

    @Override
    public List<Commit> getAllCommitsForUser(Long userId){
        Optional<User> user = this.getUserById(userId);
        return (user.isPresent()) ? user.get().getCommits(): Collections.emptyList();
    }

    public Map<Long, Long> getCommitCountByUser(){
        List<User> users = this.repository.getAllUsers();
        Map<Long, Long> map = new HashMap<Long, Long>();
        for(User u : users) {
            map.put(u.getId(), (long) u.getCommits().size());
        }
        return map;
    }

    public List<User> getUsersThatCommittedInBranch(String branchName) throws BithubException{
        Optional<Branch> branch = this.getBranchByName(branchName);
        if(! branch.isPresent()) {
            throw new BithubException("No existe el branch");
        }
        else {
            Set<User> users = new HashSet<User>();
            for(Commit c : branch.get().getCommits()){
                users.add(c.getAuthor());
            }
            return new ArrayList<User>(users);
        }
    }

    public Optional<Branch> getBranchByName(String branchName){
        return this.repository.getBranchByName(branchName);
    }


}
