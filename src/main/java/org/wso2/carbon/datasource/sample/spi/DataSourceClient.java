package org.wso2.carbon.datasource.sample.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.datasource.core.DataSourceManager;
import org.wso2.carbon.datasource.core.api.DataSourceManagementService;
import org.wso2.carbon.datasource.core.api.DataSourceService;
import org.wso2.carbon.datasource.core.exception.DataSourceException;
import org.wso2.carbon.datasource.core.impl.DataSourceManagementServiceImpl;
import org.wso2.carbon.datasource.core.impl.DataSourceServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DataSourceClient {
    private static Logger logger = LoggerFactory.getLogger(DataSourceClient.class);
    public static void main(String []args){
        DataSourceManager dataSourceManager = DataSourceManager.getInstance();
        Path configFilePath = Paths.get("src", "main", "resources", "conf", "datasources");
        DataSourceService dataSourceService = new DataSourceServiceImpl();
        DataSourceManagementService dataSourceMgtService = new DataSourceManagementServiceImpl();
        String analyticsDataSourceName = "WSO2_ANALYTICS_DB";
        try {
            dataSourceManager.initDataSources(configFilePath.toFile().getAbsolutePath());
            logger.info("Initial datasource count: " + dataSourceMgtService.getDataSource().size());
            logger.info("Found " + analyticsDataSourceName + ": " + (dataSourceService.getDataSource
                    (analyticsDataSourceName)!=null?true:false));
            dataSourceMgtService.deleteDataSource(analyticsDataSourceName);
            logger.info("Deleted " + analyticsDataSourceName + " successfully");
            logger.info("Datasource count after deleting " + analyticsDataSourceName + ": " + dataSourceMgtService.getDataSource().size());

        } catch (DataSourceException e) {
            e.printStackTrace();
        }
    }
}

