package com.app.mvc.configuration.service;

import com.app.mvc.beans.PageQuery;
import com.app.mvc.dao.ConfigurationDao;
import com.app.mvc.domain.Configuration;
import com.app.mvc.vo.ConfigurationParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jimin on 15/11/7.
 */
@Service
public class ConfigurationService {

    @Resource
    private ConfigurationDao configurationDao;

    public List<Configuration> getAll() {
        return configurationDao.getAll();
    }

    public List<Configuration> getByPage(PageQuery page) {
        return configurationDao.getByPage(page);
    }

    public int count() {
        return configurationDao.count();
    }

    public Configuration saveOrUpdate(ConfigurationParam param) {
        Configuration configuration = configurationDao.findByK(param.getK());
        if (configuration == null) {
            configuration = generate(param);
            configurationDao.insert(configuration);
        } else {
            configuration.setV(param.getV());
            configuration.setOperator(param.getOperator());
            configuration.setComment(param.getComment());
            configurationDao.updateByK(configuration);
        }
        return configuration;
    }

    private Configuration generate(ConfigurationParam param) {
        return new Configuration(param.getK(), param.getV(), param.getOperator(), param.getComment());
    }
}