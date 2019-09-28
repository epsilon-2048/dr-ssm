package epsilon.ssm;

import epsilon.ssm.service.IAspectTest;
import epsilon.ssm.service.TestService;
import epsilon.ssm.service.impl.AspectTestImpl;
import epsilon.ssm.util.LogUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Scanner;

/*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-*.xml"})
*/
public class ApplicationTest {
    @Test
    public void f(){
        int[] a = {-1,1,2,5};
        int max = 0;
        for(int i = 0; i < a.length; i++){
            int ans = 0;
            for (int j = i; j < a.length; j++) {
                ans+=a[j];
                if (max < ans)
                    max = ans;
            }
        }
        System.out.println(max);
        Math.pow(4,5);
    }
    @Test
    public void f1(){
        LogUtil logger = new LogUtil(Long.class);
        logger.info("测试");
       // logger.logException(new IllegalAccessError("除数不能为零"));
        logger.logException(new Exception(new IllegalAccessError("除数不能为零")));
    }

    int search(int[] a,int key){
        for(int i = a.length/2; i < a.length || i>-1;){
            if (key <= a[i]){
                if(i == 0 || key > a[i-1])
                    return i+1;
                else{
                    i = i / 2;
                }
            }else{
                i = (a.length+i)/2;
            }
        }
        return -1;
    }
    @Test
    public void f2(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0;i<n; i++){
            a[i] = scanner.nextInt();
            if (i != 0)
                a[i] += a[i-1];
        }
        int m = scanner.nextInt();
        for(int i = 0; i< m; i++){
            System.out.println(search(a,scanner.nextInt()));
        }
    }

}
