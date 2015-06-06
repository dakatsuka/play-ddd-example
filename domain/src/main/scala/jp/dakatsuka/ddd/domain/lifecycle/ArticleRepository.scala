package jp.dakatsuka.ddd.domain.lifecycle

import jp.dakatsuka.ddd.common.persistence.{IOContext, EntityRepository}
import jp.dakatsuka.ddd.domain.model.{Article, ArticleId}

import scala.concurrent.Future

trait ArticleRepository[S] extends EntityRepository[ArticleId, Article, S] {
  def findAllByTitle(title: String)(implicit ctx: IOContext[S]): Future[List[Article]]
}
