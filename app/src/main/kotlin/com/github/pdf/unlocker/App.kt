package com.github.pdf.unlocker

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import com.github.ajalt.mordant.terminal.Terminal
import com.itextpdf.kernel.pdf.*
import com.github.ajalt.mordant.terminal.TextColors.*
import com.github.ajalt.mordant.terminal.TextStyles.*

class App: CliktCommand() {
    private val source by option("-s", "--src", help = "The source file to unlock.").file(mustExist = true, canBeDir = false, mustBeReadable = true).required()
    private val destination by option("-d", "--dest", help = "Where the file will be saved, once unlocked.").file(canBeDir = false)
    private val password by option("-p", "--pwd", help = "The password to unlock the file.").required()
    private val inplace by option("-i", "--ip", help = "Should the file be unlocked in place.").flag(default = false)

    override fun run() {
        val pdfReader = PdfReader(source.path, ReaderProperties().setPassword(password.toByteArray()))

        val terminal = Terminal()
        val printStyle = (bold + brightBlue + underline)

        val savedFile = if (destination == null) {
            if (inplace) {
                val tempFile = source.resolveSibling("temp")
                PdfDocument(pdfReader, PdfWriter(tempFile)).close()
                tempFile.renameTo(source)
                source.path
            } else {
                val newFile = source.resolveSibling("${source.name.removeSuffix(".pdf")}_unlocked.pdf")
                PdfDocument(pdfReader, PdfWriter(newFile)).close()
                newFile.path
            }
        } else {
            PdfDocument(pdfReader, PdfWriter(destination)).close()
            destination!!.path
        }

        terminal.println(printStyle("Unlocked file and saved to: $savedFile"))
    }
}

fun main(args: Array<String>) = App().main(args)
