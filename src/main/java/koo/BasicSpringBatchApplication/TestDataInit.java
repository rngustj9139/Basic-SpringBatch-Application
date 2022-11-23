package koo.BasicSpringBatchApplication;

import koo.BasicSpringBatchApplication.core.domain.PlainText;
import koo.BasicSpringBatchApplication.core.repository.PlainTextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final PlainTextRepository plainTextRepository;

        public void dbInit1() {
            PlainText plainText1 = new PlainText();
            plainText1.setText("apple");
            plainTextRepository.save(plainText1);

            PlainText plainText2 = new PlainText();
            plainText2.setText("banana");
            plainTextRepository.save(plainText2);

            PlainText plainText3 = new PlainText();
            plainText3.setText("carrot");
            plainTextRepository.save(plainText3);

            PlainText plainText4 = new PlainText();
            plainText4.setText("dessert");
            plainTextRepository.save(plainText4);

            PlainText plainText5 = new PlainText();
            plainText5.setText("egg");
            plainTextRepository.save(plainText5);

            PlainText plainText6 = new PlainText();
            plainText6.setText("fish");
            plainTextRepository.save(plainText6);

            PlainText plainText7 = new PlainText();
            plainText7.setText("goose");
            plainTextRepository.save(plainText7);
        }

    }

}
