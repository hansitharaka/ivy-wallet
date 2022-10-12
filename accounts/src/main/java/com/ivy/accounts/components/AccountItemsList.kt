package com.ivy.accounts.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import com.ivy.accounts.data.AccountListItemWithBalanceUi
import com.ivy.accounts.data.AccountListItemWithBalanceUi.AccountHolder
import com.ivy.accounts.data.AccountListItemWithBalanceUi.FolderHolder
import com.ivy.core.ui.data.account.AccountFolderUi
import com.ivy.core.ui.data.account.AccountUi
import com.ivy.design.l1_buildingBlocks.SpacerVer

fun LazyListScope.accountItemsList(
    items: List<AccountListItemWithBalanceUi>,
    onAccountClick: (AccountUi) -> Unit,
    onFolderClick: (AccountFolderUi) -> Unit
) {
    items(
        items = items,
        key = {
            when (it) {
                is AccountHolder -> "acc_${it.account.id}"
                is FolderHolder -> "folder_${it.folder.id}"
            }
        }
    ) { item ->
        when (item) {
            is AccountHolder -> {
                SpacerVer(height = 12.dp)
                AccountCard(account = item.account, balance = item.balance) {
                    onAccountClick(item.account)
                }
            }
            is FolderHolder -> {
                SpacerVer(height = 12.dp)
                AccountFolderCard(
                    folder = item.folder,
                    balance = item.balance,
                    accounts = item.accItems,
                    onAccountClick = onAccountClick,
                    onFolderClick = { onFolderClick(item.folder) }
                )
            }
        }
    }

    // TODO: Implement empty state
}