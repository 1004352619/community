package mapper;

import java.util.List;
import model.Question;
import model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface QuestionExMapper {
    int View(Question record);
}