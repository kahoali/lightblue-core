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
package com.redhat.lightblue.query;

import com.fasterxml.jackson.databind.JsonNode;

import com.redhat.lightblue.util.Path;

public class FieldComparisonExpression 
    extends BinaryRelationalExpression {

    private Path field;
    private BinaryComparisonOperator op;
    private Path rfield;

    public Path getField() {
        return this.field;
    }

    public void setField(Path argField) {
        this.field = argField;
    }

    public BinaryComparisonOperator getOp() {
        return this.op;
    }

    public void setOp(BinaryComparisonOperator argOp) {
        this.op = argOp;
    }

    public Path getRfield() {
        return this.rfield;
    }

    public void setRfield(Path argRfield) {
        this.rfield = argRfield;
    }

    public JsonNode toJson() {
        return factory.objectNode().put("field",field.toString()).
            put("op",op.toString()).
            put("rfield",rfield.toString());
    }
}
