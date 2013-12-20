/*
    Copyright 2013 Red Hat, Inc. and/or its affiliates.

    This file is part of lightblue.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.redhat.lightblue.eval;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

import com.redhat.lightblue.metadata.FieldTreeNode;

import com.redhat.lightblue.query.RegexMatchExpression;

import com.redhat.lightblue.util.Path;

public class RegexEvaluator extends QueryEvaluator {

    private static final Logger logger=LoggerFactory.getLogger(RegexEvaluator.class);

    private final FieldTreeNode fieldMd;
    private final Pattern regex;
    private final Path relativePath;

    /**
     * Constructs evaluator for {field op value} style comparison
     *
     * @param expr The expression
     * @param md Entity metadata
     * @param context The path relative to which the expression will be evaluated
     */
    public RegexEvaluator(RegexMatchExpression expr,
                          FieldTreeNode context) {
        this.relativePath=expr.getField();
        fieldMd=context.resolve(relativePath);
        if(fieldMd==null) {
            throw new EvaluationError(expr,"No field " +relativePath);
        }
        int flags=0;
        if(expr.isCaseInsensitive()) {
            flags|=Pattern.CASE_INSENSITIVE;
        }
        if(expr.isMultiline()) {
            flags|=Pattern.MULTILINE;
        }
        if(expr.isExtended()) {
            flags|=Pattern.COMMENTS;
        }
        if(expr.isDotAll()) {
            flags|=Pattern.DOTALL;
        }
        regex=Pattern.compile(expr.getRegex(),flags);
        logger.debug("ctor {} {}",relativePath,regex);
    }

    @Override
    public boolean evaluate(QueryEvaluationContext ctx) {
        logger.debug("evaluate {} {}",relativePath,regex);
        JsonNode valueNode=ctx.getNode(relativePath);
        Object docValue;
        if(valueNode!=null) {
            docValue=fieldMd.getType().fromJson(valueNode);
        } else {
            docValue=null;
        }
        logger.debug(" value={}",valueNode);
        ctx.setResult(false);
        if(docValue!=null) {
            ctx.setResult(regex.matcher(docValue.toString()).matches());
        }
        return ctx.getResult();
    }
}
