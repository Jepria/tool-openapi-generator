package org.jepria.showcase.featureprocess;

import org.jepria.showcase.featureprocess.dao.FeatureProcessDao;
import org.jepria.showcase.featureprocess.dao.FeatureProcessDaoImpl;
import org.jepria.server.ServerFactory;
import org.jepria.server.service.rest.EntityService;
import org.jepria.server.service.rest.EntityServiceImpl;
import org.jepria.server.service.rest.SearchService;
import org.jepria.server.service.rest.SearchServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.function.Supplier;

public class FeatureProcessServerFactory extends ServerFactory<FeatureProcessDao> {

  private FeatureProcessServerFactory() {
    super(new FeatureProcessDaoImpl(), "jdbc/ITMDS");
  }

  public static FeatureProcessServerFactory getInstance() {
    return new FeatureProcessServerFactory();
  }

  public FeatureProcessService getService() {
    return new FeatureProcessService();
  }

  /**
   * @return сервис, воплощающий логику CRUD-операций (create, get-by-id, update, delete)
   */
  public EntityService getEntityService() {
    return new EntityServiceImpl(getDao(), new FeatureProcessRecordDefinition());
  }

  /**
   * @return сервис, воплощающий логику поиска объектов сущности
   */
  public SearchService getSearchService(Supplier<HttpSession> session) {
    return new SearchServiceImpl(getDao(), new FeatureProcessRecordDefinition(), session);
  }
}
