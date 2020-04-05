package com.topsail.batch.demo.example10;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;
import java.util.List;

/**
 * @author Steven
 * @date 2020-04-05
 */
public class InputOverviewDemoItemReader implements ItemReader<String> {

    private final Iterator<String> iterator;

    public InputOverviewDemoItemReader(List<String> data) {
        this.iterator = data.iterator();
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (this.iterator.hasNext()) {
            return this.iterator.next();
        } else {
            return null;
        }
    }
}
