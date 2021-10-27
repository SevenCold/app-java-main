package io.renren.modules.excel;

import java.io.File;
import java.util.Objects;

public class ExcelUtils {

    public static String getStaticPath () {
        return new File(Objects.requireNonNull(ExcelUtils.class.getResource("/static")).getPath()).getAbsolutePath();
    }
}
