package Tinder.Utils;

import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateMethodModelEx;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class CountDateFromNow implements TemplateMethodModelEx {
    public Object exec(List args){
        LocalDate now = LocalDate.now();
        Period period = Period.between((LocalDate) ((StringModel) args.get(0)).getWrappedObject(), now);
        int months = period.getMonths();
        if (months > 1){
            return String.format("%d months ago",months);
        }
        return String.format("%d days ago",period.getDays());
    }

}
