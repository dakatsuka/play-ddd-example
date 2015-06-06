package jp.dakatsuka.ddd.common.persistence

trait Identifier[+A] {
  def value: A

  val isDefined = true
  val isEmpty   = !isDefined

  override def equals(obj: Any) = obj match {
    case that: Identifier[_] =>
      value == that.value
    case _ => false
  }

  override def hashCode = 31 * value.##
}
