/*
 * Copyright (c) Microsoft Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.microsoft.playwright;


/**
 * {@code Dialog} objects are dispatched by page via the {@link Page#onDialog Page.onDialog()} event.
 *
 * <p> An example of using {@code Dialog} class:
 * <pre>{@code
 * import com.microsoft.playwright.*;
 *
 * public class Example {
 *   public static void main(String[] args) {
 *     try (Playwright playwright = Playwright.create()) {
 *       BrowserType chromium = playwright.chromium();
 *       Browser browser = chromium.launch();
 *       Page page = browser.newPage();
 *       page.onDialog(dialog -> {
 *         System.out.println(dialog.message());
 *         dialog.dismiss();
 *       });
 *       page.evaluate("alert('1')");
 *       browser.close();
 *     }
 *   }
 * }
 * }</pre>
 *
 * <p> <strong>NOTE:</strong> Dialogs are dismissed automatically, unless there is a {@link Page#onDialog Page.onDialog()} listener. When listener is
 * present, it **must** either {@link Dialog#accept Dialog.accept()} or {@link Dialog#dismiss Dialog.dismiss()} the dialog
 * - otherwise the page will <a
 * href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/EventLoop#never_blocking">freeze</a> waiting for the
 * dialog, and actions like click will never finish.
 */
public interface Dialog {
  /**
   * Returns when the dialog has been accepted.
   */
  default void accept() {
    accept(null);
  }
  /**
   * Returns when the dialog has been accepted.
   *
   * @param promptText A text to enter in prompt. Does not cause any effects if the dialog's {@code type} is not prompt. Optional.
   */
  void accept(String promptText);
  /**
   * If dialog is prompt, returns default prompt value. Otherwise, returns empty string.
   */
  String defaultValue();
  /**
   * Returns when the dialog has been dismissed.
   */
  void dismiss();
  /**
   * A message displayed in the dialog.
   */
  String message();
  /**
   * Returns dialog's type, can be one of {@code alert}, {@code beforeunload}, {@code confirm} or {@code prompt}.
   */
  String type();
}

