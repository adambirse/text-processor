package com.birse.processor.processor;

import com.birse.extractor.NumberExtractor;
import com.birse.processor.domain.Cost;
import com.birse.processor.domain.Text;
import com.birse.processor.messaging.CostSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TextProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(TextProcessor.class);


    private NumberExtractor extractor;

    private CostSender costSender;

    @Autowired
    TextProcessor(CostSender costSender) {
        this.costSender = costSender;
        extractor = new NumberExtractor();
    }

    public void process(Text text) {

        List<BigDecimal> values = extractor.extract(text.getText());
        values.stream().forEach(value ->
        {
            Cost cost = new Cost(text.getDate(), value);
            costSender.send(cost);
        });

    }
}
