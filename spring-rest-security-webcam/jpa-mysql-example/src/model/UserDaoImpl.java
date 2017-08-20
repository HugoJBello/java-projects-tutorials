package model;


import javax.persistence.EntityManager;


 
 public class UserDaoImpl implements UserDao {

	@Override
	public boolean isValidUser(String username, String password) {
		boolean isValid = false;
	    EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
	    UserPK pkToFind = new UserPK();
	    pkToFind.setUsername(username);
	    pkToFind.setPassword(password);
	    try {
			User foundUser = em.find(User.class, pkToFind);
			System.out.println("User "+ username + " found");
			isValid=true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("User "+ username + " NOT found");

		}
		return isValid;
	}

}
