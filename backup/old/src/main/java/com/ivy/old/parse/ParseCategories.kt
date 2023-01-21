package com.ivy.old.parse

import arrow.core.Either
import com.ivy.backup.base.ImportBackupError
import com.ivy.backup.base.optional
import com.ivy.backup.base.parseItems
import com.ivy.common.toUUID
import com.ivy.data.Sync
import com.ivy.data.SyncState
import com.ivy.data.category.Category
import com.ivy.data.category.CategoryState
import com.ivy.data.category.CategoryType
import org.json.JSONObject
import java.time.LocalDateTime

internal fun parseCategories(
    json: JSONObject,
    now: LocalDateTime
): Either<ImportBackupError, List<Category>> = parseItems(
    json = json,
    now = now,
    key = "categories",
    error = ImportBackupError.Parse::Categories,
    parse = {
        parseCategory(it)
    }
)

private fun JSONObject.parseCategory(
    now: LocalDateTime
): Category = Category(
    id = getString("id").toUUID(),
    name = getString("name"),
    type = CategoryType.Both,
    parentCategoryId = null,
    orderNum = getDouble("orderNum"),
    color = getInt("color"),
    icon = optional { getString("icon") },
    state = CategoryState.Default,
    sync = Sync(
        state = SyncState.Syncing,
        lastUpdated = now
    )
)