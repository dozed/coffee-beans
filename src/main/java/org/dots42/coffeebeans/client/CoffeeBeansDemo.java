package org.dots42.coffeebeans.client;

import java.util.List;

import org.dots42.coffeebeans.shared.ChangeSupport;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanFactory.Category;

public class CoffeeBeansDemo implements EntryPoint {

  public static class ResourceCategory {
    public static boolean bomb(AutoBean<Resource> instance, String what) {
      System.out.println("Bomb: " + what);
      return true;
    }
  }

  interface Resource {
    public String getId();

    public void setId(String id);

    // primitives
    public String getName();

    public void setName(String name);

    // references
    public Nested getNested();

    public void setNested(Nested nested);

    // collections
    public List<Nested> getNestedList();

    public void setNestedList(List<Nested> list);

    boolean bomb(String where);
  }

  interface Nested {
    public String getId();

    public void setId(String id);

    public String getName();

    public void setName(String name);
  }

  @Category(ResourceCategory.class)
  interface ResourceFactory extends AutoBeanFactory {
    AutoBean<Resource> resource();

    AutoBean<Nested> nested();
  }

  @Override
  public void onModuleLoad() {
    String json = "{ \"id\": \"urn:foo:resource:12931283-12398-29378jqfg4\", \"name\": \"a cool resource\", \"nested\": { \"id\": \"123\" }, \"nestedList\": []}";
    final ResourceFactory f = GWT.create(ResourceFactory.class);
    final AutoBean<Resource> res = AutoBeanCodex.decode(f, Resource.class, json);

    ChangeSupport.addObserver(new DummyChangeObserverImpl());
    
    final TextBox box = new TextBox();
    Button button = new Button("Check");
    button.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        res.as().setName(box.getValue());

        Nested nested = f.nested().as();
        res.as().getNestedList().add(nested);
        List<Nested> list = res.as().getNestedList();

        res.as().bomb("the world");

        nested.setName("blubbb");

        // List<Nested> list = new ArrayList<Nested>();
        // list.add(f.nested().as());
        // res.as().setNestedList(list);
      }
    });

    RootPanel.get().add(box);
    RootPanel.get().add(button);
  }
}
