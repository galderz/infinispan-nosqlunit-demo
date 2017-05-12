package org.infinispan.demo.nosqlunit.embedded;

import static com.lordofthejars.nosqlunit.infinispan.EmbeddedInfinispan.EmbeddedInfinispanRuleBuilder.newEmbeddedInfinispanRule;
import static com.lordofthejars.nosqlunit.infinispan.InfinispanRule.InfinispanRuleBuilder.newInfinispanRule;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import javax.inject.Inject;

import org.infinispan.commons.api.BasicCache;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.infinispan.EmbeddedInfinispan;
import com.lordofthejars.nosqlunit.infinispan.InfinispanRule;

public class WhenUserIsFoundByNameTest {

   @ClassRule
   public static final EmbeddedInfinispan EMBEDDED_INFINISPAN = newEmbeddedInfinispanRule().build();

   @Rule
   public final InfinispanRule infinispanRule = newInfinispanRule().defaultEmbeddedInfinispan();

   @Inject
   private BasicCache<String, User> embeddedCache;

   @Test
   @UsingDataSet(locations="user.json", loadStrategy= LoadStrategyEnum.CLEAN_INSERT)
   public void user_should_be_returned() {
      UserManager userManager = new UserManager(embeddedCache);
      User user = userManager.getUser("alex");

      assertThat(user, is(new User("alex", 32)));
   }

}
