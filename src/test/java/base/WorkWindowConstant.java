package base;

import java.time.ZoneOffset;

/**
 * WorkPlanConstant
 *
 * @author jianjun.li
 * @version 1.0
 * @date 2019-06-23
 */
public final class WorkWindowConstant {

    public static final ZoneOffset CHINA_ZONE = ZoneOffset.of("+8");

    public static final String TIDE_K = "3208f3937403494bb05851a02c990051";
    public static final String SENIVERSE_KEY = "St_HOctd1CYe28Chw";

    public static final double GREEN_RAINFALLS = 0;
    public static final double YELLOW_RAINFALLS = 10;

    public static final double GREEN_SPEED = 6;
    public static final double YELLOW_SPEED = 9;

    public static final double GREEN_VISIBILITIES = 1.0;
    public static final double YELLOW_VISIBILITIES = 0.5;

    public static final double GREEN_WAVE_HEIGHTS = 1.5;
    public static final double YELLOW_WAVE_HEIGHTS = 2.5;

    public static final int DATA_HOURS_LENGTH = 24 * 8;
    public static final int MAX_COMPENSATION_COUNT = 3;

    /** 最小窗口期长度 */
    public static final int MIN_WINDOW = 4;

}
