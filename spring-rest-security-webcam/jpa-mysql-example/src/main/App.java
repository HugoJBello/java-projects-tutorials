package main;

import javax.persistence.EntityManager;

import model.User;
import model.UserPK;
import model.PersistenceManager;

public class App {
  public static void main(String[] args) {
    
	// to insert data into the database:  
	User exampleUser = new User();
	UserPK pk = new UserPK();
	pk.setUsername("username4");
	pk.setPassword("pw4");
	
	exampleUser.setIdPk(pk);
	exampleUser.setName("Joe");
    exampleUser.setEmail("email@email.com");
    exampleUser.setSurname("Palomardine");
    EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
    em.getTransaction()
        .begin();
    em.persist(exampleUser);
    em.getTransaction()
        .commit();
    
    UserPK pkToFind = new UserPK();
    pkToFind.setUsername("hjbello");
    pkToFind.setPassword("1234");   
    try {
		User foundUser = em.find(User.class, pkToFind);
		System.out.println(foundUser.getEmail());
	} catch (Exception e) {
		e.printStackTrace();
	}
    
    em.close();
    PersistenceManager.INSTANCE.close();
  }
}