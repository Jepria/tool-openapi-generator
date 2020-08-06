package org.jepria.showcase.feature;

import org.jepria.showcase.feature.dao.FeatureDao;
import org.jepria.showcase.feature.dao.FeatureDaoImpl;
import org.jepria.server.ServerFactory;
import org.jepria.server.service.rest.EntityService;
import org.jepria.server.service.rest.EntityServiceImpl;
import org.jepria.server.service.rest.SearchService;
import org.jepria.server.service.rest.SearchServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.function.Supplier;

public class FeatureServerFactory extends ServerFactory<FeatureDao> {

  private FeatureServerFactory() {
    super(new FeatureDaoImpl(), "jdbc/ITMDS");
  }

  public static FeatureServerFactory getInstance() {
    return new FeatureServerFactory();
  }

  public FeatureService getService() {
    return new FeatureService();
  }

  /**
   * @return сервис, воплощающий логику CRUD-операций (create, get-by-id, update, delete)
   */
  public EntityService getEntityService() {
    return new EntityServiceImpl(getDao(), new FeatureRecordDefinition());
  }

  /**
   * @return сервис, воплощающий логику поиска объектов сущности
   */
  public SearchService getSearchService(Supplier<HttpSession> session) {
    return new SearchServiceImpl(getDao(), new FeatureRecordDefinition(), session);
  }
}
