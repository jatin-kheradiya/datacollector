/**
 * Copyright 2016 StreamSets Inc.
 *
 * Licensed under the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.streamsets.pipeline.solr.impl;

import com.streamsets.pipeline.solr.api.SdcSolrTestUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solr04TestUtil implements SdcSolrTestUtil {
  Solr04ServerUtil solrServer;
  public Solr04TestUtil(String url) throws Exception {
    solrServer = new Solr04ServerUtil(url);
  }

  public void deleteByQuery(String q) throws Exception {
    solrServer.deleteByQuery(q);
  }

  @SuppressWarnings("unchecked")
  public List<Map<String,Object>> query(Map<String, String> q) throws Exception {
    SolrQuery parameters = new SolrQuery();
    for(Map.Entry<String, String> entry : q.entrySet()) {
      parameters.set(entry.getKey(), entry.getValue());
    }
    QueryResponse response = solrServer.query(parameters);

    List<SolrDocument> solrDocumentList = response.getResults();
    List<Map<String, Object>> result = new ArrayList();
    for(SolrDocument document : solrDocumentList) {
      result.add(document);
    }
    return result;
  }

  public void destroy() {
    solrServer.destory();
  }
}
