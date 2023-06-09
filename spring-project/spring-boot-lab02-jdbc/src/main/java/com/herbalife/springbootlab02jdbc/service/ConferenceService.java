package com.herbalife.springbootlab02jdbc.service;

import static com.herbalife.springbootlab02jdbc.constants.Messages.*;

import com.herbalife.springbootlab02jdbc.exception.TopicFoundException;
import com.herbalife.springbootlab02jdbc.exception.TopicNotFoundException;
import com.herbalife.springbootlab02jdbc.model.Topic;
import com.herbalife.springbootlab02jdbc.model.TopicRecord;
import com.herbalife.springbootlab02jdbc.repository.TopicsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService {
    @Autowired
    private TopicsDao topicsDao;

    private static Logger logger = LoggerFactory.getLogger(ConferenceService.class);

    public void addTopic(String title, int duration) {
        int count = topicsDao.getCountOf(title);
        if (count == 0) {
            topicsDao.create(title, duration);
            logger.info(TOPIC_WITH_TITLE_CREATED, title);
        } else {
            throw new TopicFoundException(title);
        }
    }

    public List<Topic> getAllTopics() {
        return topicsDao.getAll();
    }

    public List<TopicRecord> getAllTopicRecords() {
        return topicsDao.getAllTopicRecords();
    }

    public void removeTopic(String title) {
        int count = topicsDao.getCountOf(title);
        if (count == 1) {
            topicsDao.delete(title);
            logger.info(TOPIC_WITH_TITLE_DELETED, title);
        } else {
            throw new TopicNotFoundException(title);
        }
    }
}
