package org.dots42.coffeebeans.shared;

import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.shared.impl.AbstractAutoBean;

/*
 * Extends AutoBeans with property change and id semantics.
 */
public abstract class CoffeeBean<T> extends AbstractAutoBean<T> {

  private ChangeSupport changeSupport = new ChangeSupport(this);
  
  private String id = "";
  
  protected CoffeeBean(AutoBeanFactory factory) {
    super(factory);
  }

  protected CoffeeBean(AutoBeanFactory factory, Splittable data) {
    super(factory, data);
  }

  protected CoffeeBean(T wrapped, AutoBeanFactory factory) {
    super(wrapped, factory);
  }

  // save id
  // TODO check on code generation if getId is present
  @Override
  public void setData(Splittable data) {
    super.setData(data);
    id = getOrReify("id");
  }
  
  public String getId() {
    return id;
  }
  
  public ChangeSupport getChangeSupport() {
    return changeSupport;
  }

  // setter interception
  public void beforeSet(String propertyName, Object value, Object old) {
    changeSupport.handleSet(propertyName, value, old);
  }
  
}
