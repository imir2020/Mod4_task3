package jun.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
   public static SessionFactory buildSessionFactory(){
       Configuration configuration = new Configuration();
       configuration.configure();
      // configuration.addAttributeConverter(new BirthdayConvertor());
       return configuration.buildSessionFactory();
   }
}
