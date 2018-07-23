package xyz.taotao.search.test;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.junit.Test;

public class SolrClientDelById {

    /**
     * 第一步：创建一个SolrServer对象。
     * 第二步：调用SolrServer对象的根据id删除的方法。
     * 第三步：提交。
     *
     * @throws Exception
     */
    @Test
    public void deleteDocumentById() throws Exception {
        // 第一步：创建一个SolrServer对象。
        SolrServer solrServer = new HttpSolrServer("http://192.168.184.130:8080/solr");
        // 第二步：调用SolrServer对象的根据id删除的方法。
        solrServer.deleteById("1");
        // 第三步：提交。
        solrServer.commit();
    }

}
