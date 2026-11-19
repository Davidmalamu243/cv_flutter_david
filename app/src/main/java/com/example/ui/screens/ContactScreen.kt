package com.example.ui.screens

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.ContactActionType
import com.example.model.ContactItem
import com.example.ui.components.AnimatedProCard
import com.example.ui.components.ProSectionHeader
import com.example.ui.components.ProStatusBadge

@Composable
fun ContactScreen(
    contacts: List<ContactItem>,
    shareSummary: String,
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val copiedLabel = stringResource(R.string.contact_copied)
    val noAppLabel = stringResource(R.string.contact_no_app)

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(key = "contact_header") {
            Column(modifier = Modifier.widthIn(max = 720.dp)) {
                ProSectionHeader(
                    title = stringResource(R.string.contact_title),
                    accentColor = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = stringResource(R.string.contact_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        itemsIndexed(contacts, key = { _, item -> item.id }) { index, contact ->
            ContactActionCard(
                item = contact,
                animationIndex = index,
                onLaunch = {
                    launchContactIntent(
                        context = context,
                        item = contact,
                        onFailure = { onShowMessage(noAppLabel) }
                    )
                },
                onCopy = {
                    copyToClipboard(context, contact.label, contact.copyValue)
                    onShowMessage("$copiedLabel : ${contact.copyValue}")
                },
                modifier = Modifier.widthIn(max = 720.dp)
            )
        }

        item(key = "structures_highlight") {
            Column(modifier = Modifier.widthIn(max = 720.dp)) {
                Spacer(modifier = Modifier.height(4.dp))
                ProSectionHeader(
                    title = "Structures & Projets",
                    accentColor = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(8.dp))
                AnimatedProCard(animationIndex = contacts.size) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        ProStatusBadge(
                            text = "Bukavu • Éducation",
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Complexe Scolaire MALKIA",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Promoteur — Contact via +243 997 658 995",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f))
                    Spacer(modifier = Modifier.height(14.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        ProStatusBadge(
                            text = "Goma • Production musicale",
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "DUNDA Production",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Fondateur — Booking & collaboration : davselmalamu0@gmail.com",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        item(key = "share_button") {
            Column(modifier = Modifier.widthIn(max = 720.dp)) {
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    onClick = {
                        val sendIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, "CV - Ciza Elmalamu David")
                            putExtra(Intent.EXTRA_TEXT, shareSummary)
                        }
                        val shareIntent = Intent.createChooser(sendIntent, "Partager le CV de David")
                        try {
                            context.startActivity(shareIntent)
                        } catch (_: ActivityNotFoundException) {
                            onShowMessage(noAppLabel)
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("contact_share_cv_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(R.string.action_share_cv),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun ContactActionCard(
    item: ContactItem,
    animationIndex: Int,
    onLaunch: () -> Unit,
    onCopy: () -> Unit,
    modifier: Modifier = Modifier
) {
    val icon = when (item.type) {
        ContactActionType.PHONE -> Icons.Default.Call
        ContactActionType.EMAIL -> Icons.Default.Email
        ContactActionType.MAP -> Icons.Default.LocationOn
        ContactActionType.WEB -> Icons.Default.Public
    }
    val accent = when (item.type) {
        ContactActionType.PHONE -> MaterialTheme.colorScheme.tertiary
        ContactActionType.EMAIL -> MaterialTheme.colorScheme.primary
        ContactActionType.MAP -> MaterialTheme.colorScheme.secondary
        ContactActionType.WEB -> MaterialTheme.colorScheme.primary
    }

    AnimatedProCard(
        modifier = modifier.testTag(item.id),
        animationIndex = animationIndex,
        onClick = onLaunch
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(accent.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = item.label,
                    tint = accent,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.label,
                    style = MaterialTheme.typography.labelMedium,
                    color = accent
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = item.value,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = item.actionHint,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(
                onClick = onCopy,
                modifier = Modifier
                    .minimumInteractiveComponentSize()
                    .testTag("${item.id}_copy")
            ) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = stringResource(R.string.contact_copy),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                contentDescription = null,
                tint = accent,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

private fun launchContactIntent(
    context: Context,
    item: ContactItem,
    onFailure: () -> Unit
) {
    val action = when (item.type) {
        ContactActionType.PHONE -> Intent.ACTION_DIAL
        ContactActionType.EMAIL -> Intent.ACTION_SENDTO
        ContactActionType.MAP, ContactActionType.WEB -> Intent.ACTION_VIEW
    }
    val intent = Intent(action, Uri.parse(item.actionUri))
    try {
        context.startActivity(intent)
    } catch (_: ActivityNotFoundException) {
        onFailure()
    }
}

private fun copyToClipboard(context: Context, label: String, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText(label, text))
}
