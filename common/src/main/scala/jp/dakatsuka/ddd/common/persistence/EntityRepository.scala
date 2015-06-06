package jp.dakatsuka.ddd.common.persistence

import scala.concurrent.Future

trait EntityRepository[ID <: Identifier[_], E <: Entity[ID], S] extends IOContextTransparently[S] {
  def find(id: ID)(implicit ctx: IOContext[S]): Future[Option[E]]
}
