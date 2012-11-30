/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lombok.intellij.processor.clazz;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;
import de.plushnikov.intellij.lombok.ErrorMessages;
import de.plushnikov.intellij.lombok.problem.ProblemBuilder;
import de.plushnikov.intellij.lombok.processor.clazz.AbstractLombokClassProcessor;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;

/**
 * @author Andres Almiray
 */
public abstract class AbstractGriffonLombokClassProcessor extends AbstractLombokClassProcessor {
    private final Class<? extends Annotation> annotationClass;

    public AbstractGriffonLombokClassProcessor(@NotNull Class<? extends Annotation> annotationClass, @NotNull Class<?> psiClass) {
        super(annotationClass, psiClass);
        this.annotationClass = annotationClass;
    }

    protected Class<? extends Annotation> getAnnotationClass() {
        return this.annotationClass;
    }

    @Override
    protected boolean validate(@NotNull PsiAnnotation psiAnnotation, @NotNull PsiClass psiClass, @NotNull ProblemBuilder builder) {
        return validateAnnotationOnRigthType(psiClass, builder);
    }

    protected boolean validateAnnotationOnRigthType(@NotNull PsiClass psiClass, @NotNull ProblemBuilder builder) {
        boolean result = true;
        if (psiClass.isAnnotationType() || psiClass.isInterface() || psiClass.isEnum()) {
            builder.addError(ErrorMessages.canBeUsedOnClassOnly(getAnnotationClass()));
            result = false;
        }
        return result;
    }

    public PsiElementFactory psiElementFactory(PsiClass psiClass) {
        Project project = psiClass.getProject();
        return JavaPsiFacade.getElementFactory(project);
    }
}
