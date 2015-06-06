package jp.dakatsuka.ddd.infrastructure.lifecycle

import jp.dakatsuka.ddd.common.persistence.IOContext
import jp.dakatsuka.ddd.domain.lifecycle.ArticleRepository
import jp.dakatsuka.ddd.domain.model.{Article, ArticleId}
import scalikejdbc._, async._, FutureImplicits._

import scala.concurrent.Future

class ScalikeJDBCArticleRepository extends ScalikeJDBCRepository[AsyncDBSession] with ArticleRepository[AsyncDBSession] {
  override type ID = ArticleId
  override type E  = Article

  object Table extends SQLSyntaxSupport[E] {
    override val tableName = "articles"
  }

  override protected def convertToNamedValues(article: E): Seq[(Symbol, Any)] =
    Seq('id -> article.id, 'title -> article.title)

  override protected def convertToEntity(n: ResultName[E])(rs: WrappedResultSet): E =
    Article(
      id = ArticleId(rs.string(n.id)),
      title = rs.string(n.title)
    )

  def findAllByTitle(title: String)(implicit ctx: IOContext[AsyncDBSession]): Future[List[E]] = withSQL {
    selectFrom(tableName).where.eq(Table.column.title, title)
  }.map(toEntity).list()
}
