import com.msg.proj.entities.User;
import com.msg.proj.repository.IRepo;
import com.msg.proj.repository.RepoFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class UTest {

    private static EntityManager em;

    @Before
    public static void start() {
        em = Persistence.createEntityManagerFactory("min_proj").createEntityManager();
    }

    @Test
    public void testFindUser() {
        IRepo<User, String> userRepo = RepoFactory.createRepo();
        String expectedCNP = "2931207350046";
        User user = userRepo.read(User.class, expectedCNP);

        Assert.assertEquals(expectedCNP, user.getCNP());
    }
}
