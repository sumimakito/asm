/* Software Name : AsmDex
 * Version : 1.0
 *
 * Copyright © 2012 France Télécom
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holders nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.objectweb.asmdex.util;

import org.objectweb.asmdex.ApplicationVisitor;
import org.objectweb.asmdex.ClassVisitor;

/**
 * Application Adapter used to shift all the registers found in the bytecode.
 * 
 * This is done by overriding the visitClass method of this ApplicationAdapter, like shown below.
 * 
 * @author Julien Névo
 */
public class RegisterShiftApplicationAdapter extends ApplicationVisitor{

	protected RegisterShiftClassAdapter shiftClassAdapter;

	/**
	 * Shift registers in application
	 * @param av application visitor
	 * @param shiftClassAdapter adapter for shifting class
	 */
	public RegisterShiftApplicationAdapter(ApplicationVisitor av, RegisterShiftClassAdapter shiftClassAdapter) {
		super(av);
		this.shiftClassAdapter = shiftClassAdapter;
	}
	
	@Override
	public ClassVisitor visitClass(int access, String name, String[] signature,
			String superName, String[] interfaces) {
		
		ClassVisitor cv = av.visitClass(access, name, signature, superName, interfaces);
		shiftClassAdapter.setClassVisitor(cv);
		shiftClassAdapter.setClassName(name);
		return shiftClassAdapter;
		//RegisterShiftClassAdapter adapter = new RegisterShiftClassAdapter(cv, name);
		//return adapter;
	}

}