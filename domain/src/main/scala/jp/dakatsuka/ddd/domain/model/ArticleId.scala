package jp.dakatsuka.ddd.domain.model

import jp.dakatsuka.ddd.common.persistence.Identifier

case class ArticleId(value: String) extends Identifier[String]
