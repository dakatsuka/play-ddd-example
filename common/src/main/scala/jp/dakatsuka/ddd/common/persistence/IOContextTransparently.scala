package jp.dakatsuka.ddd.common.persistence

trait IOContextTransparently[S] {
  implicit def wrap(implicit session: S): IOContext[S] = IOContext(session)
  implicit def unwrap(implicit ctx: IOContext[S]): S = ctx.session
}
