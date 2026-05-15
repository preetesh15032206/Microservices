/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
 */

import { Download, Github, Terminal, CheckCircle } from 'lucide-react';

export default function App() {
  return (
    <div className="min-h-screen bg-gray-50 flex flex-col items-center justify-center p-6 text-gray-800">
      <div className="max-w-2xl bg-white p-8 rounded-xl shadow-xl border border-gray-100">
        <div className="w-16 h-16 bg-green-100 text-green-600 rounded-full flex items-center justify-center mx-auto mb-6">
          <CheckCircle className="w-8 h-8" />
        </div>
        
        <h1 className="text-3xl font-bold text-gray-900 mb-2 text-center">Java Spring Boot Project Generated!</h1>
        <p className="text-gray-600 mb-8 text-center text-lg">
          Your complete <strong>Employee Management System</strong> (Java 17 + Spring Boot + MySQL + Thymeleaf + Security) has been created successfully.
        </p>
        
        <div className="bg-blue-50 border border-blue-100 text-blue-900 p-5 rounded-lg mb-6">
          <h3 className="font-bold mb-3 flex items-center text-blue-800">
            <Download className="w-5 h-5 me-2" /> How to access your code:
          </h3>
          <ol className="list-decimal list-inside space-y-2 mb-4">
            <li>Click the <strong>Settings</strong> gear icon (top right of the compiler editor)</li>
            <li>Select <strong className="text-blue-700">Export as ZIP</strong></li>
            <li>Extract the folder and open it in IntelliJ IDEA or VS Code!</li>
          </ol>
        </div>
        
        <div className="bg-gray-800 text-gray-100 p-5 rounded-lg text-sm mb-6">
          <h4 className="font-bold mb-2 flex items-center text-gray-300">
            <Terminal className="w-4 h-4 me-2" /> Local Setup Commands:
          </h4>
          <pre className="bg-gray-900 p-3 rounded font-mono text-green-400 overflow-x-auto">
            $ cd java-backend/<br/>
            $ mvn clean install<br/>
            $ mvn spring-boot:run
          </pre>
        </div>

        <div className="text-sm text-gray-500 border-t pt-4 text-center">
          <p>* Note: AI Studio's live preview window natively runs Node.js frontends.</p>
          <p>Please download the exported code to run your Java environment natively.</p>
        </div>
      </div>
    </div>
  );
}
