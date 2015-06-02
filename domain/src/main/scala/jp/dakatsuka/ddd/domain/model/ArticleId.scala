package jp.dakatsuka.ddd.domain.model

import jp.dakatsuka.ddd.domain.persistence.Identifier

case class ArticleId(value: String) extends Identifier[String]
