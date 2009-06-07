/*
 * Copyright 2009 Bart Guijt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.code.gwt.database.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Represents the JSO version of a SQLResultSetRowList.
 * 
 * @see SQLResultSetRowList
 * @author bguijt
 */
public class SQLResultSetRowListJso<T extends JavaScriptObject> extends JavaScriptObject {

  protected SQLResultSetRowListJso() {}
  
  public final native int getLength() /*-{
    return this.length;
  }-*/;
  
  public final native T getItem(int index) /*-{
    return this.item(index);
  }-*/;
}
