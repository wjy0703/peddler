package cn.com.cucsi.app.web.util.log;

import java.util.Comparator;


public class SortLogComparator implements Comparator<SingleLogInfo> {

    @Override
    public int compare(SingleLogInfo logone, SingleLogInfo logtwo) {
        String first = logone.getName();
        String second = logtwo.getName();
        return first.compareTo(second);        
    }

}
