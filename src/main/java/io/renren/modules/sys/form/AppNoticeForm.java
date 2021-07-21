package io.renren.modules.sys.form;

import lombok.Data;

@Data
public class AppNoticeForm {

    private String title;

    private String content;

    private boolean ring = true;

    private boolean vibrate = true;

}
