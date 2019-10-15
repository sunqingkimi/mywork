package base;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.apache.log4j.Logger;
import lombok.extern.slf4j.Slf4j;

import static jdk.nashorn.internal.objects.NativeMath.log;


/**
 * 默认状态为3种：A(value:0)、B(value:1)、C(value:2)，假定窗口最大4（大于等于4不处理）
 *
 *
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class StatusServiceTest {

    @InjectMocks
    private StatusService statusService;
    protected Log log = LogFactory.getLog(getClass());
    static LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);

    /**
     * 有A在前面，(X:1~N). eg. Ax,B2,B3 --> Ax,B5
     */
    @Test
    public void merge0() {
        int[] status = { 0, 0, 0, 1, 1, 2, 2, 2 };
        int[] expecteds = { 0, 0, 0, 2, 2, 2, 2, 2 };
        double[] ws = { 0, 0, 0, 2, 2, 2, 2, 2 };
        int[] actuals = statusService.statusMerge(startTime, status, ws);
        log("actuals : {}", actuals);
        log("expecteds : {}", expecteds);
        Assert.assertEquals(expecteds, actuals);
        System.out.println();
    }

    /**
     * 有A在前面，(X:1~N). eg. Ax,B2,Ax,B3 --> A2x+5
     */
    @Test
    public void merge1() {
        int[] status1 = { 0, 0, 0, 1, 1, 0, 0, 0, 1, 1 };
        int[] expecteds1 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        double[] ws1 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        int[] actuals1 = statusService.statusMerge(startTime, status1, ws1);
        log("actuals1 : {}", actuals1);
        log("expecteds1 : {}", expecteds1);
        Assert.assertEquals(expecteds1, actuals1);
        System.out.println();
    }

    /**
     * 两边状态相同，中间状态数量比两边小 C3,B2,C3 --> C8
     */
    @Test
    public void merge2() {

        int[] status2 = { 2, 2, 2, 1, 1, 2, 2, 2 };
        int[] expecteds2 = { 2, 2, 2, 2, 2, 2, 2, 2 };
        double[] ws2 = { 2, 2, 2, 2, 2, 2, 2, 2 };
        int[] actuals2 = statusService.statusMerge(startTime, status2, ws2);
        log("actuals : {}", actuals2);
        log("expecteds : {}", expecteds2);
        Assert.assertEquals(expecteds2, actuals2);
        System.out.println();
    }

    /**
     * 两边状态相同，中间状态数量比两边多 C2,B3,C2 --> B7
     */
    @Test
    public void merge3() {
        int[] status3 = { 2, 2, 1, 1, 1, 2, 2 };
        int[] expecteds3 = { 1, 1, 1, 1, 1, 1, 1 };
        double[] ws3 = { 1, 1, 1, 1, 1, 1, 1 };
        int[] actuals3 = statusService.statusMerge(startTime, status3, ws3);
        log("actuals : {}", actuals3);
        log("expecteds : {}", expecteds3);
        Assert.assertEquals(expecteds3, actuals3);
        System.out.println();
    }

    /**
     * 两边状态不同，中间状态少 C3,B2,A3 --> C5,A3
     */
    @Test
    public void merge4() {
        int[] status4 = { 2, 2, 2, 1, 1, 0, 0 };
        int[] expecteds4 = { 2, 2, 2, 2, 2, 0, 0 };
        double[] ws4 = { 2, 2, 2, 2, 2, 0, 0 };
        int[] actuals4 = statusService.statusMerge(startTime, status4, ws4);
        log("actuals : {}", actuals4);
        log("expecteds : {}", expecteds4);
        Assert.assertEquals(expecteds4, actuals4);
        System.out.println();
    }

    /**
     * 两边状态不同，中间状态多 C3,B5,A3 --> B8,A3
     */
    @Test
    public void merge5() {
        int[] status5 = {2,1};
        int[] expecteds5 = {1,1};
        double[] ws5 = {6.01,6.001};
        int[] actuals5 = statusService.statusMerge(startTime, status5, ws5);
        for(int a=0;a<actuals5.length;a++){
            System.out.println(actuals5[a]);
        }
        Assert.assertEquals(expecteds5, actuals5);
        System.out.println();
    }

    /**
     * 两边状态不同，中间状态多 C3,B5,C3 --> B11
     */
    @Test
    public void merge6() {
        int[] status6 = { 0,1,1,1,1,2,2,2,2,0};
        int[] expecteds6 = { 0,1,1,1,1,2,2,2,2,0 };
        double[] ws6 = { 0,7,8,6.1,6.2,1,2,3,4,0};
        int[] actuals6 = statusService.statusMerge(startTime, status6, ws6);
        log("actuals : {}", actuals6);
        log("expecteds : {}", expecteds6);
        Assert.assertEquals(expecteds6, actuals6);
        System.out.println();
    }

    /**
     * 两边状态不同，中间状态多 C3,B5,C3 --> B11
     */
    @Test
    public void merge7() {
        int[] status7 = { 0,1,2,1,2,2,2,0 };
        int[] expecteds7 = { 0,2,2,2,2,2,2,0};
        double[] ws7 = { 10,6.4,5.5,6.2,5.9,5.7,5.9,10 };
        int[] actuals7 = statusService.statusMerge(startTime, status7, ws7);
        log("actuals : {}", actuals7);
        log("expecteds : {}", expecteds7);
        Assert.assertEquals(expecteds7, actuals7);
        System.out.println();
    }

    /**
     * 有A在前面，(X:1~N). eg. Ax,B2,B3 --> Ax,B5
     */
    @Test
    public void merge8() {
        int[] status8 = { 1, 1, 1, 1, 0, 0, 2, 2 };
        int[] expecteds8 = { 1, 1, 1, 1, 0, 0, 0, 0 };
        double[] ws8 = { 1, 1, 1, 1, 0, 0, 0, 0 };
        int[] actuals8 = statusService.statusMerge(startTime, status8, ws8);
        log("actuals : {}", actuals8);
        log("expecteds : {}", expecteds8);
        Assert.assertEquals(expecteds8, actuals8);
        System.out.println();
    }

    /**
     * 长度一样，根据2个状态边界风速判断,B更靠近边界，B权重最大. eg. B3,C3,B2 --> C8
     */
    @Test
    public void merge9() {
        int[] status9 = { 1, 1, 1, 2, 2, 2, 1, 1 };
        int[] expecteds9 = { 1, 1, 1, 1, 1, 1, 1, 1 };
        double[] ws9 = { 6.2, 6.8, 6.2, 5.6, 4, 5.9, 4, 5 };
        int[] actuals9 = statusService.statusMerge(startTime, status9, ws9);
        log("actuals : {}", actuals9);
        log("expecteds : {}", expecteds9);
        Assert.assertEquals(expecteds9, actuals9);
        System.out.println();
    }

    /**
     * 长度一样，根据2个状态边界风速判断. eg. B3,C3,B2 --> B8
     */
    @Test
    public void merge10() {
        int[] status10 = { 2, 2, 2, 1, 1, 1, 2, 2 };
        int[] expecteds10 = { 1, 1, 1, 1, 1, 1, 1, 1 };
        double[] ws10 = { 5.6, 4, 5.5, 6.2, 6.8, 7, 4, 5 };
        int[] actuals10 = statusService.statusMerge(startTime, status10, ws10);
        log("actuals : {}", actuals10);
        log("expecteds : {}", expecteds10);
        Assert.assertEquals(expecteds10, actuals10);
        System.out.println();
    }
}
