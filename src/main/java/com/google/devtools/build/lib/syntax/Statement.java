// Copyright 2014 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.lib.syntax;


/**
 * Base class for all statements nodes in the AST.
 */
public abstract class Statement extends ASTNode {

  /**
   * Executes the statement in the specified build environment, which may be
   * modified.
   *
   * @throws EvalException if execution of the statement could not be completed.
   * @throws InterruptedException may be thrown in a sub class.
   */
  final void exec(Environment env) throws EvalException, InterruptedException   {
    try {
      doExec(env);
    } catch (EvalException ex) {
      throw maybeTransformException(ex);
    }
  }

  /**
   * Executes the statement.
   *
   * <p>This method is only invoked by the super class {@link Statement} when calling {@link
   * #exec(Environment)}.
   *
   * @throws EvalException if execution of the statement could not be completed.
   * @throws InterruptedException may be thrown in a sub class.
   */
  abstract void doExec(Environment env) throws EvalException, InterruptedException;
}
