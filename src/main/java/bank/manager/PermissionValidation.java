package bank.manager;

import bank.model.UserBean;
import bank.util.FrozenUserException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

@Aspect
public class PermissionValidation extends HibernateDaoSupport{

    @Before(value = "execution(void bank.manager.ManagerImpl.* (int,..))&&args(id,..)")
    public void checkVerification(int id) throws FrozenUserException{
        UserBean userBean = this.getHibernateTemplate().get(UserBean.class,id);
        if (!userBean.isEnable()){
            throw new FrozenUserException();
        }
    }
}
