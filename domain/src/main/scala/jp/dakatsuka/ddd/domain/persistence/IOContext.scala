package jp.dakatsuka.ddd.domain.persistence

case class IOContext[S](session: S)
