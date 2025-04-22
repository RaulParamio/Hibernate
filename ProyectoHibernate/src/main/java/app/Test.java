package app;

import org.hibernate.Session;

import util.HibernateUtil;

public class Test {

	public static void main(String[] args) {

		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

			System.out.println("ok");

		}

		catch (Exception e) {

			e.printStackTrace();

		} finally {

		HibernateUtil.closeSessionFactory();

		}

	}

}
