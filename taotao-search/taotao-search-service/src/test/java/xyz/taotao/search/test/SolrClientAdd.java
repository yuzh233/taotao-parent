package xyz.taotao.search.test;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 测试操作 Solr 索引库
 */
public class SolrClientAdd {

    /**
     * 第一步：把solrJ的jar包添加到工程中。
     * 第二步：创建一个SolrServer，使用HttpSolrServer创建对象。
     * 第三步：创建一个文档对象SolrInputDocument对象。
     * 第四步：向文档中添加域。必须有id域，域的名称必须在schema.xml中定义。
     * 第五步：把文档添加到索引库中。
     * 第六步：提交。
     *
     * @throws Exception
     */
    @Test
    public void addDocument() throws Exception {
        // 第一步：把solrJ的jar包添加到工程中。
        // 第二步：创建一个SolrServer，使用HttpSolrServer创建对象。
        SolrServer solrServer = new HttpSolrServer("http://192.168.184.130:8080/solr");
        // 第三步：创建一个文档对象SolrInputDocument对象。
        SolrInputDocument document = new SolrInputDocument();
        // 第四步：向文档中添加域。必须有id域，域的名称必须在schema.xml中定义。
        document.addField("id", "test001");
        document.addField("item_title", "测试商品");
        document.addField("item_price", "199");
        // 第五步：把文档添加到索引库中。
        solrServer.add(document);
        // 第六步：提交。
        solrServer.commit();
    }




}
