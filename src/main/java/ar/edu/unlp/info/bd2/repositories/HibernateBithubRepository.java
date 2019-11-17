package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.services.BithubException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class HibernateBithubRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Object object) {
        Session session = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            session.save(object);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public Optional<Commit> getCommitByHash(String aHash) {
        Session session = null;
        Commit c = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            c = (Commit) session.createQuery("from Commit c where c.hash = :aHash").setParameter("aHash", aHash).uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (c != null) ? Optional.of(c):Optional.empty();
    }

    public Optional<Branch> getBranchByName(String branchName) {
        Session session = null;
        Branch branch = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            branch = (Branch) session.createQuery("from Branch b where b.name = :bName").setParameter("bName", branchName).uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {  }
        }
        return (branch != null) ? Optional.of(branch):Optional.empty();
    }

    public Optional<Tag> getTagByName(String tagName) {
        Session session = null;
        Tag tag = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            tag = (Tag) session.createQuery("from Tag t where t.name = :tName").setParameter("tName", tagName).uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) { }
        }
        return (tag != null) ? Optional.of(tag):Optional.empty();
    }

    public Optional<Review> getReviewById(long id) {
        Session session = null;
        Review review = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            review = (Review) session.createQuery("from Review where id = :rId").setParameter("rId", id).uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) { }
        }
        return (review != null) ? Optional.of(review):Optional.empty();
    }

    public Optional<User> getUserById(Long id) {
        Session session = null;
        User user = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            user = (User) session.createQuery("from User where id = :uId").setParameter("uId", id).uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (user != null) ? Optional.of(user):Optional.empty();
    }

    public List<User> getAllUsers() {
        Session session = null;
        List<User> users = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            users = session.createQuery("from User").list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) { }
        }
        return users;
    }

    public Optional<User> getUserByEmail(String userEmail) {
        Session session = null;
        User user = null;
        try {
            session = this.sessionFactory.getCurrentSession();
            user = (User) session.createQuery("from User where email = :uEmail").setParameter("uEmail", userEmail).uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) { }
        }
        return (user != null) ? Optional.of(user):Optional.empty();
    }
}
