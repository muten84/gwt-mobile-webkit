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

package com.google.code.gwt.database.client.service.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Provides the SQL statement(s) to be executed when the annotated method is
 * called.
 * 
 * <p>
 * Provide any statement parameters between curly braces, e.g.:
 * </p>
 * 
 * <pre>
 * &#x40;SQL(stmt="INSERT INTO mytable (when, name) VALUES(<b>{when.getTime()}</b>, <b>{name}</b>)")
 * void insertData(Date <b>when</b>, String <b>name</b>, VoidCallback callback);
 * </pre>
 * 
 * <h3>Collection input parameters</h3>
 * 
 * <p>
 * The SQL annotation can also be used to repeat a (set of) SQL statement(s) for
 * each item in an {@link Iterable} collection or an Array. This mechanism is
 * expressed like this:
 * </p>
 * 
 * <pre>
 * &#x40;SQL(stmt="INSERT INTO mytable (when, name) VALUES (<b>{#.getTime()}</b>, {name})",
 *     foreach="<b>dates</b>")
 * void insertData(Iterable&lt;Date&gt; <b>dates</b>, VoidCallback callback);
 * </pre>
 * 
 * <p>
 * The important parts are emphasized in <b>bold</b>. The above specification is
 * translated to Java like this:
 * </p>
 * 
 * <pre>
 * for (Date <b>value</b> : <b>dates</b>) {
 *   tx.executeSql("INSERT INTO mytable (when, name) VALUES (<b>?</b>, ?)",
 *       new Object[] {<b>value.getTime()</b>, name});
 * }
 * </pre>
 * 
 * <p>
 * All statements are executed within the same database transaction.
 * </p>
 * 
 * <h3>SQL dialect</h3>
 * 
 * <p>
 * Up to now (oct. 2009) all HTML5 Database implementations use SQLite, which
 * has its own <a href="http://www.sqlite.org/lang.html">SQL flavor</a>.
 * </p>
 * 
 * @see <a href="http://www.sqlite.org/lang.html">SQLite3 SQL Language
 *      Reference</a>
 * @author bguijt
 */
@Documented
@Target(ElementType.METHOD)
public @interface SQL {

  /**
   * Provides one or more SQL statements (of any <code>SELECT</code>,
   * <code>INSERT</code>, <code>UPDATE</code> etc. type) to be executed whenever
   * the annotated method is called.
   */
  String[] stmt();

  /**
   * Specifies the name of the service method parameter representing an
   * {@link Iterable} (or Array) collection of input values to process.
   * 
   * <p>
   * If this attribute is specified, the statement(s) from {@link #stmt()} are
   * executed in a loop iterating over this Collection. If you
   * want to provide the 'current' Collection item to the statement as
   * parameter, use the 'hash' character (<code>#</code>) as a substitute as in
   * e.g. <code>{#.getTime()}</code>.
   * </p>
   */
  String foreach() default "";
}