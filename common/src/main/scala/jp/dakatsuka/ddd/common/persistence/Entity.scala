package jp.dakatsuka.ddd.common.persistence

trait Entity[ID <: Identifier[_]] {
  val id: ID

  override def equals(obj: Any): Boolean = obj match {
    case that: Entity[_] => id == that.id
    case _               => false
  }

  override def hashCode: Int = 31 * id.##
}
