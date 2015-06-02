package jp.dakatsuka.ddd.domain.model

import jp.dakatsuka.ddd.domain.persistence.Entity

case class Article(id: ArticleId, title: String) extends Entity[ArticleId]
