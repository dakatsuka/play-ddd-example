package jp.dakatsuka.ddd.domain.persistence

trait IOContextTransparently[S] {
  implicit def wrap(implicit session: S): IOContext[S] = IOContext(session)
  implicit def unwrap(implicit ctx: IOContext[S]): S = ctx.session
}
